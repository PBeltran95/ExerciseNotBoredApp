package ar.com.example.notbored.domain.repository

import ar.com.example.notbored.data.Category
import ar.com.example.notbored.data.RecreationalActivity

object DataRepository {

    val listOfEducation = listOf(RecreationalActivity("Learn Programming", 0.0,categoryName = "Education" ),
        RecreationalActivity("Learn a Language", 0.5,categoryName = "Education"),
        RecreationalActivity("Learn Maths", 0.0,categoryName = "Education"))

    val listOfRecreational = listOf(RecreationalActivity("Go run to park", 0.0, categoryName = "Recreational"),
        RecreationalActivity("Go shopping", 0.99, categoryName = "Recreational"),
        RecreationalActivity("Buy a present", 0.7, categoryName = "Recreational")
    )
    val listOfSocial = listOf(RecreationalActivity("Go to the club", 0.7, categoryName = "Social"),
        RecreationalActivity("Go for a walk with your friends", 0.0, categoryName = "Social"),
        RecreationalActivity("Use tinder", 0.99, categoryName = "Social")
    )
    val listOfDiy = listOf(RecreationalActivity("Repair the garden", 0.0, categoryName = "Diy"),
        RecreationalActivity("Try to repair something", 0.5, categoryName = "Diy"),
        RecreationalActivity("Build a furniture", 0.0, categoryName = "Diy")
    )
    val listOfCharity = listOf(RecreationalActivity("Be a voluntary", 0.0, categoryName = "Charity"),
        RecreationalActivity("Give a present", 0.5, categoryName = "Charity"),
        RecreationalActivity("Be part of a solidarity collection", 0.3, categoryName = "Charity")
    )
    val listOfCooking = listOf(RecreationalActivity("Cook a cake", 0.2, categoryName = "Cooking"),
        RecreationalActivity("Cook something new", 0.8, categoryName = "Cooking"),
        RecreationalActivity("Cook a pizza", 0.4, categoryName = "Cooking")
    )
    val listOfRelaxation = listOf(RecreationalActivity("Meditation", 0.0, categoryName = "Relaxation"),
        RecreationalActivity("Go for a walk with the dog", 0.0, categoryName = "Relaxation"),
        RecreationalActivity("Sing a song", 0.0, categoryName = "Relaxation")
    )
    val listOfMusic = listOf(RecreationalActivity("Listen classic music", 0.2, categoryName = "Music"),
        RecreationalActivity("Listen old music", 0.0, categoryName = "Music"),
        RecreationalActivity("Sing the first thing on the radio", 0.0, categoryName = "Music")
    )
    val listOfBusywork = listOf(RecreationalActivity("Do homework", 0.0, categoryName = "Busywork"),
        RecreationalActivity("Clean the floor", 0.3, categoryName = "Busywork"),
        RecreationalActivity("Find a job", 0.8, categoryName = "Busywork")
    )
    val listOfMovies = listOf(RecreationalActivity("Watch DiCaprio movies", 0.0, categoryName = "Movies"),
        RecreationalActivity("Watch netflix", 0.3, categoryName = "Movies"),
        RecreationalActivity("Go to cinema", 0.7, categoryName = "Movies")
    )
    val listOfVideoGames = listOf(RecreationalActivity("Play Counter Strike", 0.3, categoryName = "Video games"),
        RecreationalActivity("Play Mario Bros", 0.7, categoryName = "Video games"),
        RecreationalActivity("Buy a Pc gamer", 0.9, categoryName = "Video games")
    )

    val listOfCategories = listOf(
        Category("Education", listOfEducation),
        Category("Recreational", listOfRecreational),
        Category("Social", listOfSocial),
        Category("Diy", listOfDiy),
        Category("Charity", listOfCharity),
        Category("Cooking", listOfCooking),
        Category("Relaxation", listOfRelaxation),
        Category("Music", listOfMusic),
        Category("Busywork", listOfBusywork),
        Category("Movies", listOfMovies),
        Category("Video games", listOfVideoGames),
    )

}