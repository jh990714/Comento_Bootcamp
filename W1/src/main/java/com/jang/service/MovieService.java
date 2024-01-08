package com.jang.service;

import java.util.List;
import com.jang.vo.MovieVO;
 
public interface MovieService {
    public List<MovieVO> selectMovie() throws Exception;
}