#include <iostream>
#include <vector>
#include <fstream>
#include <sstream>
#include <algorithm>
#include "../../Stopwatch/Stopwatch.h"

int64_t CountTwoIntsEqualToValue(const std::vector<int64_t> array, int64_t left, int64_t value)
{
    int64_t counter = 0, counterLeftEqs = 0, counterRightEqs = 0;
    int64_t right = array.size() - 1;
    while (left < right)
    {
        while (left + 1 < right && array[left] + array[right] > value)
        {
            --right;
        }

        counterLeftEqs = 1;
        while (left + 1 < right && array[left + 1] == array[left])
        {
            ++counterLeftEqs;
            ++left;
        }

        counterRightEqs = 1;
        while (right - 1 > left && array[right - 1] == array[right])
        {
            ++counterRightEqs;
            --right;
        }

        if (left >= right)
        {
            std::cout << "Err" << std::endl;
        }

        if (array[left] + array[right] == value)
        {
            counter += counterLeftEqs * counterRightEqs;
        }

        ++left;
    }

    return counter;
}

int64_t ThreeSumFaster(std::vector<int64_t>& array)
{
    std::sort(array.begin (), array.end());

    int64_t counter = 0;
    for (int64_t i = 0; i < array.size(); ++i)
    {
        int64_t value = array[i];
        counter += CountTwoIntsEqualToValue(array, i + 1, -value);
    }
    return counter;
}

int64_t ThreeSum(const std::vector<int64_t>& array)
{
    int64_t counter = 0;
    for (int i = 0; i < array.size(); ++i)
    {
        for (int j = i + 1; j < array.size(); ++j)
        {
            for (int k = j + 1; k < array.size(); ++k)
            {
                if (array[i] + array[j] + array[k] == 0)
                {
                    ++counter;
                }
            }
        }
    }
    return counter;
}

int main()
{
    //std::ifstream ifs("../../algs4-data/1Kints.txt");
    //std::ifstream ifs("../../algs4-data/2Kints.txt");
    //std::ifstream ifs("../../algs4-data/4Kints.txt");
    std::ifstream ifs("../../algs4-data/8Kints.txt");

    std::string line;
    std::vector<int64_t> array;
    while (getline(ifs, line))
    {
        int64_t i;
        std::stringstream ss;
        ss << line;
        ss >> i;
        array.push_back(i);
    }
    ifs.close();

    StopWatch s2;
    std::cout << "ThreeSumFaster () = " << ThreeSumFaster(array) << std::endl;
    s2.tick();
    std::cout << "Time: " << s2.getTimeElapsed() << std::endl;

    s2.tick();
    std::cout << "ThreeSum () = " << ThreeSum(array) << std::endl;
    s2.tick();
    std::cout << "Time: " << s2.getTimeElapsed() << std::endl;

    return 0;
}
