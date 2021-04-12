import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.app.chuckit.R
import com.app.chuckit.ui.fragments.NorrisFactsFragment
import com.app.chuckit.ui.fragments.SearchNorrisFactsFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChuckItActivityTest {

    @Test
    fun garante_navegacao_entre_norris_facts_para_search_norris_facts() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        runOnUiThread {
            navController.setGraph(R.navigation.navigation_graph)
        }
        val norrisFactsFragmentScenario = launchFragmentInContainer<NorrisFactsFragment>()

        norrisFactsFragmentScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        onView(withText(R.string.search_chuck_norris_facts)).perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.searchNorrisFactsFragment)
    }

    @Test
    fun garante_navegacao_entre_search_norris_facts_para_norris_facts() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        runOnUiThread {
            navController.setGraph(R.navigation.navigation_graph)
            navController.navigate(R.id.searchNorrisFactsFragment)
            navController.navigate(R.id.norrisFactsFragment)
        }
        val norrisFactsFragmentScenario =
            launchFragmentInContainer<SearchNorrisFactsFragment>(
                null,
                R.style.AppTheme
            )

        norrisFactsFragmentScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.editTextSearch))
            .perform(typeText("trump"), pressImeActionButton())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.searchNorrisFactsFragment)
    }
}