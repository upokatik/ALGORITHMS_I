#include <iostream>
#include <vector>

typedef const std::vector<int64_t> TIntVector;

// Cycle-based binary search in normal sorted array
int64_t BinarySearchCycle(const TIntVector& array, int64_t value)
{
    int64_t low = 0, high = array.size() - 1;
    while (low <= high)
    {
        int64_t mid = low + (high - low) / 2;
        if (value < array[mid]) high = mid - 1;
        else if (value > array[mid]) low = mid + 1;
        else return mid;
    }
    return -1;
}

// Recursion-based binary search in normal sorted array
int64_t BinarySearchRecursive(const TIntVector& array, int64_t low, int64_t high, int64_t value)
{
    if (low <= high)
    {
        int64_t mid = low + (high - low) / 2;
        if (value < array[mid])      return BinarySearchRecursive(array, low, mid - 1, value);
        else if (value > array[mid]) return BinarySearchRecursive(array, mid + 1, high, value);
        else return mid;
    }
    return -1;
}

// Recursion-based binary search in bitonic sorted array
int64_t BinarySearchBitonic(const TIntVector& array, int64_t low, int64_t high, int64_t value)
{
    if (low == high && array[low] == value)
    {
        return low;
    }
    else if (low < high)
    {
        int64_t mid = low + (high - low) / 2;
        if (value > array[mid])
        {
            // value might be to the left OR to the right of mid, not both
            if (array[mid + 1] > array[mid])
            {
                return BinarySearchBitonic(array, mid + 1, high, value);
            }
            else if (array[mid + 1] < array[mid])
            {
                return BinarySearchBitonic(array, low, mid - 1, value);
            }
        }
        else if (value < array[mid])
        {
            if (value >= array[low] && value >= array[high])
            {
                // value might be to the BOTH left and to the right of mid
                // checking left part first
                int64_t r = BinarySearchBitonic(array, low, mid - 1, value);
                if (r != -1)
                {
                    return r;
                }
                // if value is not in the left part, checking right part
                return BinarySearchBitonic(array, mid + 1, high, value);
            }
            else
            {
                if (array[low] < array[high])
                {
                    return BinarySearchBitonic(array, low, mid - 1, value);
                }
                else if (array[low] > array[high])
                {
                    return BinarySearchBitonic(array, mid + 1, high, value);
                }
            }
        }
        else return mid;
    }
    return -1;
}

int main()
{
    // Binary search in normal sorted array

    int64_t value = 12;
    std::vector<int64_t> sortedArray =
            { -4, -1, 3, 9, 10, 11, 12, 12, 34 };
//             0   1  2  3   4   5   6   7   8

    int64_t rc = BinarySearchCycle(sortedArray, value);
    std::cout << "ResultCycle = " << rc << std::endl;

    int64_t rr = BinarySearchRecursive(sortedArray, 0, sortedArray.size() - 1, value);
    std::cout << "ResultRecursive = " << rr << std::endl;

    // Binary search in bitonic array

    int64_t valueBitonic = -51;
    std::vector<int64_t> bitonicArray =
            { 1, 3, 4, 6, 9, 14, 11, 7, 2, -4, -9, -10, -15, -17, -21, -24, -26, -30, -40, -42, -44, -45, -50, -55 };
//            0  1  2  3  4   5   6  7  8   9  10   11   12   13   14   15   16   17   18   19   20   21   22   23

    int64_t rb = BinarySearchBitonic(bitonicArray, 0, bitonicArray.size() - 1, valueBitonic);
    std::cout << "ResultBitonic = " << rb << std::endl;

    return 0;
}
