package com.example.myapplication.data.Repository



import com.example.myapplication.data.Entities.Product
import com.example.myapplication.R
import kotlinx.coroutines.delay
import javax.inject.Inject


class ProductRepository @Inject constructor() {


    suspend fun getProducts(): List<Product> {
        delay(2000) //  Simulate network or database delay

        return listOf(
            Product(
                id = "1",
                name = "Fajr",
                price = 19.99,
                imageResId = R.drawable.image1,
                description = "Une tasse inspirée par la sérénité de l’aube, symbole d’un nouveau départ",
                reviews = 5.0
            ),
            Product(
                id = "2",
                name = "Noor",
                price = 22.50,
                imageResId = R.drawable.image2,
                description = "Élégante et lumineuse, cette tasse dorée apporte chaleur à chaque instant café",
                reviews = 5.0
            ),
            Product(
                id = "3",
                name = "Layali",
                price = 25.00,
                imageResId = R.drawable.image3,
                description = "Un hommage aux nuits orientales, parfaite pour les moments calmes et profonds",
                reviews = 5.0
            ),
            Product(
                id = "4",
                name = "Sama",
                price = 21.75,
                imageResId = R.drawable.image4,
                description = "Un design céleste aux nuances bleues, pour s’évader à chaque gorgée",
                reviews = 5.0
            ),
            Product(
                id = "5",
                name = "Rimal",
                price = 18.90,
                imageResId = R.drawable.image5,
                description = "Inspirée des dunes dorées, cette tasse incarne la beauté du désert",
                reviews = 5.0
            ),
            Product(
                id = "6",
                name = "Andalous",
                price = 29.90,
                imageResId = R.drawable.image6,
                description = "Un style raffiné aux motifs andalous, alliant histoire et élégance",
                reviews = 5.0
            ),
            Product(
                id = "7",
                name = "Haneen",
                price = 24.60,
                imageResId = R.drawable.image7,
                description = "Délicate et nostalgique, elle évoque la douceur des souvenirs",
                reviews = 5.0
            ),
            Product(
                id = "8",
                name = "Rüya Classique",
                price = 27.00,
                imageResId = R.drawable.image8,
                description = "Le modèle emblématique de Rüya, où l’art rencontre l’authenticité",
                reviews = 5.0
            ),
            Product(
                id = "9",
                name = "Asrar",
                price = 23.40,
                imageResId = R.drawable.image9,
                description = "Mystérieuse et artistique, cette tasse séduit les amateurs de beauté singulière",
                reviews = 5.0
            )
        )
    }
}
