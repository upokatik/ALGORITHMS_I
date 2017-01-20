#ifndef STOP_H
#define STOP_H

#include <chrono>

class StopWatch
{
public:
    StopWatch();
    void tick();
    template<class T = float> T getTimeElapsed() const;

private:
    std::chrono::high_resolution_clock::time_point newTime, oldTime;
};

StopWatch::StopWatch()
{
    oldTime = std::chrono::high_resolution_clock::now();
    newTime = oldTime;
}

void StopWatch::tick()
{
    oldTime = newTime;
    newTime = std::chrono::high_resolution_clock::now();
}

// Returns time elapsed in seconds
template<class T>
T StopWatch::getTimeElapsed() const
{
    using std::chrono::duration_cast;
    using std::chrono::duration;
    return (duration_cast<duration<T>>(newTime - oldTime)).count();
}

#endif // STOP_H

