package com.jang.dao;

import java.util.List;
import com.jang.vo.MovieVO;
 
public interface MovieDAO {
    public List<MovieVO> selectMovie() throws Exception;
}