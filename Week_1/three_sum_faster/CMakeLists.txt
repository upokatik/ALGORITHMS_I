cmake_minimum_required(VERSION 3.6)
project(three_sum_faster)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(three_sum_faster ${SOURCE_FILES})