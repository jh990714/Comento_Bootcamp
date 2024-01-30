import sys
import traceback
import datetime as date
import MySQLdb as mdb
import requests

# 요청 url 리턴해주는 함수
def get_request_query(url, operation, params, serviceKey, type):
    import urllib.parse as urlparse
    params = urlparse.urlencode(params)
    request_query = url + '/' + operation + '?' + params + '&' + 'serviceKey' + '=' + serviceKey + '&' + '_type' + '=' + type
    return request_query

try:

    # 현재년도 가져오기
    today = date.datetime.now()
    today_year = today.strftime('%Y')

    print('[' + str(date.datetime.now().strftime('%Y.%M.%d %H:%M:%S')) + ']' + ' 공휴일 배치 시작')

    # 2년간 데이터만 조회되므로 내년 데이터까지만 확인
    search_year = [int(2023)]
    search_month = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']

    # 공휴일 요청 API URL
    URL = 'http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService'
    # 국경일 + 공휴일 정보 조회
    OPERATION = 'getHoliDeInfo'
    # 서비스키
    SERVICEKEY = '서비스 키 값'
    # 데이터 타입
    TYPE = 'json'

    # DB에 넣을 데이터
    result_date = []
    result_name = []
    result_is_holiday = []

    # API 요청
    for year in search_year:
        # 파라미터
        for month in search_month:
            PARAMS = {'solYear': year, 'solMonth': month}

            request_query = get_request_query(URL, OPERATION, PARAMS, SERVICEKEY, TYPE)
            response = requests.get(url=request_query)
            items = response.json().get('response').get('body').get('items')

            # 데이터가 있을때만
            if (items != ''):
                holidays = items.get('item')

                # 타입체크
                if ( type(holidays) == type({})):
                    day_name = holidays.get('dateName')
                    is_holiday = holidays.get('isHoliday')
                    holiday_dt = holidays.get('locdate')

                    result_date.append(str(holiday_dt))
                    result_name.append(day_name)
                    result_is_holiday.append(is_holiday)
                elif (type(holidays) == type([])):
                    for holiday in holidays:
                        day_name = holiday.get('dateName')
                        is_holiday = holiday.get('isHoliday')
                        holiday_dt = holiday.get('locdate')

                        result_date.append(str(holiday_dt))
                        result_name.append(day_name)
                        result_is_holiday.append(is_holiday)

    # DB 접속
    conn = mdb.connect("127.0.0.1","root","password입력","statistc")
    cur = conn.cursor(mdb.cursors.DictCursor)

    cur.execute("SET NAMES utf8")
    cur.execute("SET CHARACTER SET utf8")
    cur.execute("SET character_set_connection=utf8")

    insert_count = 0
    # 공휴일 정보 입력
    for i in range(len(result_date)):
        sql = "INSERT INTO HOLIDAYS (holiday_dt, day_name, is_holiday) VALUES (%s, %s, %s)"
        val = (result_date[i], result_name[i], result_is_holiday[i])
        cur.execute(sql, val)
        insert_count += 1

    print('[' + str(insert_count) +'건 등록]')
    print('[' + str(date.datetime.now().strftime('%Y.%M.%d %H:%M:%S')) + ']' + ' 공휴일 배치 종료')

    conn.commit()

except Exception as ex:
    if conn:
        conn.rollback()
        conn.commit()
    print ("Error " , ex)
    print (traceback.format_exc())
    sys.exit(1)