import { MobileApi } from "../types/MobileApi";

async function fetchData(): Promise<MobileApi[]> {
  try {
    const response = await fetch("https://fakestoreapi.com/products");
    if (!response.ok) {
      throw new Error("Failed to fetch data");
    }
    const data = await response.json();
    const formattedData: MobileApi[] = data.map((item: any) => ({
      id: item.id,
      title: item.title,
      price: item.price,
      description: item.description,
      category: item.category,
      image: item.image,
      rating: {
        rate: item.rating.rate,
        count: item.rating.count,
      },
    }));
    return formattedData;
  } catch (error) {
    console.error("Error fetching data:", error);
    return [];
  }
}

export default fetchData;
