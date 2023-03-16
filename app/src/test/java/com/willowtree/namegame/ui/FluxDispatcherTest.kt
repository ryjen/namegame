package com.willowtree.namegame.ui

import com.willowtree.namegame.MainDispatcherRule
import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.flux.FluxDispatcher
import com.willowtree.namegame.ui.flux.FluxStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FluxDispatcherTest {

    data class StateA(
        val a: Int = 0
    )

    data class StateB(val b: Int = 0)

    data class ActionA(val a: Int = 0) : Action
    data class ActionB(val b: Int = 0) : Action

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `flux dispatcher can dispatch actions between stores`() {
        val scope = TestScope()

        val dispatcher = FluxDispatcher(scope)

        val storeA = dispatcher.createStore(StateA()).addReducer { state, action ->
            when (action) {
                is ActionA -> state.copy(a = action.a)
                is ActionB -> state.copy(a = -action.b)
                else -> state
            }
        } as FluxStore<StateA>

        val storeB = dispatcher.createStore(StateB()).addReducer { state, action ->
            when (action) {
                is ActionA -> state.copy(b = -action.a)
                is ActionB -> state.copy(b = action.b)
                else -> state
            }
        } as FluxStore<StateB>

        storeA.listen {
            println("StateA: $it")
        }

        storeB.listen {
            println("StateB: $it")
        }

        dispatcher.dispatch(ActionA(a = 2))

        runTest {

            delay(1)

            assertEquals(2, storeA.state().value.a)

            assertEquals(-2, storeB.state().value.b)

        }

        dispatcher.dispatch(ActionB(b = 6))

        runTest {

            delay(1)

            assertEquals(-6, storeA.state().value.a)

            assertEquals(6, storeB.state().value.b)

        }
    }
}
