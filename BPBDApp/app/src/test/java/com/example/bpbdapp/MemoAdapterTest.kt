package com.example.bpbdapp

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MemoAdapterTest {

    @Test
    fun getItemCount_returnsCorrectSize() {
        val memos = listOf(
            Memo("1", "Title 1", "Message 1", 1L),
            Memo("2", "Title 2", "Message 2", 2L)
        )
        val adapter = MemoAdapter(memos)
        assertEquals(2, adapter.itemCount)
    }
}
