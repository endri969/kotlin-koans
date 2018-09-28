package rx

import io.reactivex.Observable
import org.junit.Test

class RxKotlinTest {

    @Test fun testReturnAValue(){
        var result = ""

        val observer = Observable.just("Hello")
        observer.subscribe { result = it }
        assert(result == "Hello")
    }
}