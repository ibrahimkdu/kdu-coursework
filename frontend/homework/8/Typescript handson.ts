interface IRecipe {
    id: number;
    name: string;
    ingredients: string[];
    instructions: string[];
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    servings: number;
    difficulty: string;
    cuisine: string;
    caloriesPerServing: number;
    tags: string[];
    userId: number;
    image: string;
    rating: number;
    reviewCount: number;
    mealType: string[];
}

interface IRequiredRecipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    timeTaken: number;
    caloriesPerServing: number;
}

class RecipeSearchApplication {
    private recipes: IRecipe[] = [];

    async fetchRecipesFromAPI(): Promise<void> {
        try {
            const response = await fetch('https://dummyjson.com/recipes');
            const data = await response.json();
            this.recipes = data.recipes.map((recipe: IRecipe) => this.mapRecipeToRequiredFormat(recipe)); // Map to required format
            console.log('Recipes fetched from API:', this.recipes);
        } catch (error) {
            console.error('Error fetching recipes:', error);
        }
    }

    async searchRecipes(query: string): Promise<void> {
        try {
            const response = await fetch(`https://dummyjson.com/recipes/search?q=${query}`);
            const data = await response.json();
            this.recipes = data.recipes.map((recipe: IRecipe) => this.mapRecipeToRequiredFormat(recipe)); // Map to required format
            console.log('Recipes matching query:', this.recipes);
        } catch (error) {
            console.error('Error searching recipes:', error);
        }
    }

    printAllRecipesInRequiredFormat(): void {
        const recipesInRequiredFormat: IRequiredRecipe[] = this.recipes.map(recipe => this.mapRecipeToRequiredFormat(recipe));
        recipesInRequiredFormat.forEach(recipe => {
            console.log('Recipe in required format:', recipe);
        });
    }

    mapRecipeToRequiredFormat(recipe: IRecipe): IRequiredRecipe {
        const prepTime = typeof recipe.prepTimeMinutes === 'string' ? parseInt(recipe.prepTimeMinutes, 10) : recipe.prepTimeMinutes;
        const cookTime = typeof recipe.cookTimeMinutes === 'string' ? parseInt(recipe.cookTimeMinutes, 10) : recipe.cookTimeMinutes;
    
        // Check if prepTime or cookTime are NaN
        if (isNaN(prepTime) || isNaN(cookTime)) {
            console.log('Invalid prepTimeMinutes or cookTimeMinutes:', recipe.prepTimeMinutes, recipe.cookTimeMinutes);
        }
    
        // Calculate timeTaken
        const timeTaken = prepTime + cookTime;
    
        // Check if timeTaken is NaN
        if (isNaN(timeTaken)) {
            console.log('Invalid timeTaken:', prepTime, cookTime);
        }
        return {
            image: recipe.image,
            name: recipe.name,
            rating: recipe.rating,
            cuisine: recipe.cuisine,
            ingredients: recipe.ingredients,
            timeTaken: timeTaken,
            caloriesPerServing: recipe.caloriesPerServing
        };
    }
}

const app = new RecipeSearchApplication();
app.fetchRecipesFromAPI().then(() => {
    app.printAllRecipesInRequiredFormat(); // Print all recipes in the required format

    app.searchRecipes('pizza').then(() => {
        app.printAllRecipesInRequiredFormat(); // Print searched recipes in the required format
    });
});



