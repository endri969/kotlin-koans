package iii_conventions

import kotlin.Comparable

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year   -> year - other.year
        month != other.month -> month - other.month
        else                 -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this,other)

operator fun MyDate.plus(interval: TimeInterval) = this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(repeatedInterval: RepeatedTimeInterval) =
        this.addTimeIntervals(repeatedInterval.interval, repeatedInterval.occurences)

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate>{

//    override fun contains(d: MyDate): Boolean = d>= start && d<= endInclusive


    override fun iterator(): Iterator<MyDate> = DateIterator(DateRange(start,endInclusive))

}
class DateIterator(private val dateRange: DateRange): Iterator<MyDate> {

    private var current = dateRange.start

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val nextDate = current
        current = current.nextDay()
        return nextDate
    }
}

class RepeatedTimeInterval(val interval: TimeInterval, val occurences: Int)