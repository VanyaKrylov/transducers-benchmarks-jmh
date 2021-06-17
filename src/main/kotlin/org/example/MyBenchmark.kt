package org.example

import ik.TestsContainer
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 3)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(3)
open class MyBenchmark {
    lateinit var tc: TestsContainer
    lateinit var v: List<Int>
    lateinit var vHi: List<Int>
    lateinit var vLo: List<Int>
    lateinit var vFaZ: List<Int>
    lateinit var vZaF: List<Int>

    @Setup
    fun setup() {
        tc = TestsContainer()
        v    = (0..100000000).map { it % 10 }.dropLast(1)
        vHi  = (0..10000000).map { (it % 10) }.dropLast(1)
        vLo  = (0..10).map { (it % 10) }.dropLast(1)
        vFaZ = (0..10000).toMutableList().dropLast(1)
        vZaF = (0..10000000).toMutableList().dropLast(1)
    }

    @TearDown
    fun tearDown() {
        val sum = 450000000
        val sumOfSquares = 2850000000
        val sumOfSquaresEven = 1200000000
        val cart = 2025000000
        val mapsMegamorphic = 2268000000000
        val filtersMegamorphic = 170000000
        val flatMapTake = 405000000
        val dotProduct = 285000000
        val flatMapAfterZip = 1499850000000
        val zipAfterFlatMap = 99999990000000
        val zipFlatMapFlatMap = 315000000

        with(tc) {
            //Inlined
            assert(sum(v) == sum)
            assert(sumOfSquares(v) == sumOfSquares)
            assert(sumOfSquaresEven(v) == sumOfSquaresEven)
            assert(cart(vHi, vLo) == cart)
            assert(cartFused(vHi, vLo) == cart)
            assert(mapsMegamorphic(v) == mapsMegamorphic)
            assert(filtersMegamorphic(v) == filtersMegamorphic)
            assert(flatMapTake(vHi, vLo) == flatMapTake)
            assert(flatMapTakeFused(vHi, vLo) == flatMapTake)
            assert(dotProduct(vHi) == dotProduct)
            assert(flatMapAfterZip(vFaZ) == flatMapAfterZip)
            assert(flatMapAfterZipFused(vFaZ) == flatMapAfterZip)
            assert(zipAfterFlatMap(vZaF) == zipAfterFlatMap)
            assert(zipAfterFlatMapFused(vZaF) == zipAfterFlatMap)
            assert(zipFlatMapFlatMap(v, vLo) == zipFlatMapFlatMap)
            assert(zipFlatMapFlatMapFused(v, vLo) == zipFlatMapFlatMap)
            assert(sumNI(v) == sum)
            assert(sumOfSquaresNI(v) == sumOfSquares)
            assert(sumOfSquaresEvenNI(v) == sumOfSquaresEven)
            assert(cartNI(vHi, vLo) == cart)
            assert(cartFusedNI(vHi, vLo) == cart)
            assert(mapsMegamorphicNI(v) == mapsMegamorphic)
            assert(filtersMegamorphicNI(v) == filtersMegamorphic)
            assert(flatMapTakeNI(vHi, vLo) == flatMapTake)
            assert(flatMapTakeFusedNI(vHi, vLo) == flatMapTake)
            assert(dotProductNI(vHi) == dotProduct)
            assert(flatMapAfterZipNI(vFaZ) == flatMapAfterZip)
            assert(flatMapAfterZipFusedNI(vFaZ) == flatMapAfterZip)
            assert(zipAfterFlatMapNI(vZaF) == zipAfterFlatMap)
            assert(zipAfterFlatMapFusedNI(vZaF) == zipAfterFlatMap)
            assert(zipFlatMapFlatMapNI(v, vLo) == zipFlatMapFlatMap)
            assert(zipFlatMapFlatMapFusedNI(v, vLo) == zipFlatMapFlatMap)
            assert(sumSeq(v) == sum)
            assert(sumOfSquaresSeq(v) == sumOfSquares)
            assert(sumOfSquaresEvenSeq(v) == sumOfSquaresEven)
            assert(cartSeq(vHi, vLo) == cart)
            assert(cartSeqMixed(vHi, vLo) == cart)
            assert(mapsMegamorphicSeq(v) == mapsMegamorphic)
            assert(filtersMegamorphicSeq(v) == filtersMegamorphic)
            assert(flatMapTakeSeq(vHi, vLo) == flatMapTake)
            assert(flatMapTakeSeqMixed(vHi, vLo) == flatMapTake)
            assert(dotProductSeq(vHi) == dotProduct)
            assert(flatMapAfterZipSeq(vFaZ) == flatMapAfterZip)
            assert(flatMapAfterZipSeqMixed(vFaZ) == flatMapAfterZip)
            assert(zipAfterFlatMapSeq(vZaF) == zipAfterFlatMap)
            assert(zipAfterFlatMapSeqMixed(vZaF) == zipAfterFlatMap)
            assert(zipFlatMapFlatMapSeq(v, vLo) == zipFlatMapFlatMap)
        }
    }

    @Benchmark
    fun sum(): Int {
        return tc.sum(v)
    }

    @Benchmark
    fun sumOfSquares(): Long {
        return tc.sumOfSquares(v)
    }

    @Benchmark
    fun sumOfSquaresEven(): Int {
        return tc.sumOfSquaresEven(v)
    }

    @Benchmark
    fun cart(): Int {
        return tc.cart(vHi, vLo)
    }

    @Benchmark
    fun cartFused(): Int {
        return tc.cartFused(vHi, vLo)
    }

    @Benchmark
    fun mapsMegamorphic(): Long {
        return tc.mapsMegamorphic(v)
    }

    @Benchmark
    fun filtersMegamorphic(): Int {
        return tc.filtersMegamorphic(v)
    }

    @Benchmark
    fun flatMapTake(): Int {
        return tc.flatMapTake(vHi, vLo)
    }

    @Benchmark
    fun flatMapTakeFused(): Int {
        return tc.flatMapTakeFused(vHi, vLo)
    }

    @Benchmark
    fun dotProduct(): Int {
        return tc.dotProduct(vHi)
    }

    @Benchmark
    fun flatMapAfterZip(): Long {
        return tc.flatMapAfterZip(vFaZ)
    }

    @Benchmark
    fun flatMapAfterZipFused(): Long {
        return tc.flatMapAfterZipFused(vFaZ)
    }

    @Benchmark
    fun zipAfterFlatMap(): Long {
        return tc.zipAfterFlatMap(vZaF)
    }

    @Benchmark
    fun zipAfterFlatMapFused(): Long {
        return tc.zipAfterFlatMapFused(vZaF)
    }

    @Benchmark
    fun zipFlatMapFlatMap(): Int {
        return tc.zipFlatMapFlatMap(v, vLo)
    }

    @Benchmark
    fun zipFlatMapFlatMapFused(): Int {
        return tc.zipFlatMapFlatMapFused(v, vLo)
    }

    @Benchmark
    fun sumNI(): Int {
        return tc.sumNI(v)
    }

    @Benchmark
    fun sumOfSquaresNI(): Long {
        return tc.sumOfSquaresNI(v)
    }

    @Benchmark
    fun sumOfSquaresEvenNI(): Int {
        return tc.sumOfSquaresEvenNI(v)
    }

    @Benchmark
    fun cartNI(): Int {
        return tc.cartNI(vHi, vLo)
    }

    @Benchmark
    fun cartFusedNI(): Int {
        return tc.cartFusedNI(vHi, vLo)
    }

    @Benchmark
    fun mapsMegamorphicNI(): Long {
        return tc.mapsMegamorphicNI(v)
    }

    @Benchmark
    fun filtersMegamorphicNI(): Int {
        return tc.filtersMegamorphicNI(v)
    }

    @Benchmark
    fun flatMapTakeNI(): Int {
        return tc.flatMapTakeNI(vHi, vLo)
    }

    @Benchmark
    fun flatMapTakeFusedNI(): Int {
        return tc.flatMapTakeFusedNI(vHi, vLo)
    }

    @Benchmark
    fun dotProductNI(): Int {
        return tc.dotProductNI(vHi)
    }

    @Benchmark
    fun flatMapAfterZipNI(): Long {
        return tc.flatMapAfterZipNI(vFaZ)
    }

    @Benchmark
    fun flatMapAfterZipFusedNI(): Long {
        return tc.flatMapAfterZipFusedNI(vFaZ)
    }

    @Benchmark
    fun zipAfterFlatMapNI(): Long {
        return tc.zipAfterFlatMapNI(vZaF)
    }

    @Benchmark
    fun zipAfterFlatMapFusedNI(): Long {
        return tc.zipAfterFlatMapFusedNI(vZaF)
    }

    @Benchmark
    fun zipFlatMapFlatMapNI(): Int {
        return tc.zipFlatMapFlatMapNI(v, vLo)
    }

    @Benchmark
    fun zipFlatMapFlatMapFusedNI(): Int {
        return tc.zipFlatMapFlatMapFusedNI(v, vLo)
    }

    @Benchmark
    fun sumSeq(): Int {
        return tc.sumSeq(v)
    }

    @Benchmark
    fun sumOfSquaresSeq(): Long {
        return tc.sumOfSquaresSeq(v)
    }

    @Benchmark
    fun sumOfSquaresEvenSeq(): Int {
        return tc.sumOfSquaresEvenSeq(v)
    }

    @Benchmark
    fun cartSeq(): Int {
        return tc.cartSeq(vHi, vLo)
    }

    @Benchmark
    fun cartSeqMixed(): Int {
        return tc.cartSeqMixed(vHi, vLo)
    }

    @Benchmark
    fun mapsMegamorphicSeq(): Long {
        return tc.mapsMegamorphicSeq(v)
    }

    @Benchmark
    fun filtersMegamorphicSeq(): Int {
        return tc.filtersMegamorphicSeq(v)
    }

    @Benchmark
    fun flatMapTakeSeq(): Int {
        return tc.flatMapTakeSeq(vHi, vLo)
    }

    @Benchmark
    fun flatMapTakeSeqMixed(): Int {
        return tc.flatMapTakeSeqMixed(vHi, vLo)
    }

    @Benchmark
    fun dotProductSeq(): Int {
        return tc.dotProductSeq(vHi)
    }

    @Benchmark
    fun flatMapAfterZipSeq(): Long {
        return tc.flatMapAfterZipSeq(vFaZ)
    }

    @Benchmark
    fun flatMapAfterZipSeqMixed(): Long {
        return tc.flatMapAfterZipSeqMixed(vFaZ)
    }

    @Benchmark
    fun zipAfterFlatMapSeq(): Long {
        return tc.zipAfterFlatMapSeq(vZaF)
    }

    @Benchmark
    fun zipAfterFlatMapSeqMixed(): Long {
        return tc.zipAfterFlatMapSeqMixed(vZaF)
    }

    @Benchmark
    fun zipFlatMapFlatMapSeq(): Int {
        return tc.zipFlatMapFlatMapSeq(v, vLo)
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val options = OptionsBuilder()
                .include(MyBenchmark::class.java.simpleName)
                .build()

            Runner(options).run()
        }
    }
}