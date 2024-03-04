import React, { useEffect, useState, useContext } from "react";
import { Link } from "react-router-dom";
import { ProductContext } from "../context/ProductContext";
import fetchData from "../utils/api";
import { MobileApi } from "../types/MobileApi";
import SearchBar from "./SearchBar";
import BrandFilter from "./BrandFilter";
import PriceSort from "./PriceSort";

const ProductList: React.FC = () => {
  const { setSelectedProduct } = useContext(ProductContext);
  const [products, setProducts] = useState<MobileApi[]>([]);
  const [filteredProducts, setFilteredProducts] = useState<MobileApi[]>([]);
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [selectedBrand, setSelectedBrand] = useState<string>("");
  const [sortOrder, setSortOrder] = useState<string>("");
  const [brands, setBrands] = useState<string[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      const data = await fetchData();
      setProducts(data.slice(0, 15)); // Limiting to 15 products
      setFilteredProducts(data.slice(0, 15));
      // Extracting unique brands from the data
      const uniqueBrands = [
        ...new Set(data.map((product) => product.category)),
      ];
      setBrands(uniqueBrands);
    };
    fetchProducts();
  }, []);

  useEffect(() => {
    // Filter products based on search query
    const filtered = products.filter((product) =>
      product.title.toLowerCase().includes(searchQuery.toLowerCase())
    );
    setFilteredProducts(filtered);
  }, [searchQuery, products]);

  useEffect(() => {
    // Filter products based on selected brand
    const filtered = products.filter((product) =>
      selectedBrand ? product.category === selectedBrand : true
    );
    setFilteredProducts(filtered);
  }, [selectedBrand, products]);

  useEffect(() => {
    // Sort products based on sort order
    if (sortOrder === "asc") {
      setFilteredProducts((prevProducts) =>
        [...prevProducts].sort((a, b) => a.price - b.price)
      );
    } else if (sortOrder === "desc") {
      setFilteredProducts((prevProducts) =>
        [...prevProducts].sort((a, b) => b.price - a.price)
      );
    }
  }, [sortOrder]);

  const handleSearch = (query: string) => {
    setSearchQuery(query);
  };

  const handleSelectBrand = (brand: string) => {
    setSelectedBrand(brand);
  };

  const handleSortChange = (order: string) => {
    setSortOrder(order);
  };

  return (
    <div>
      <div
        style={{
          display: "flex",
          marginBottom: "10px",
          alignItems: "center",
          backgroundColor: "#2a2a72",
        }}
      >
        <SearchBar onSearch={handleSearch} />
        <BrandFilter brands={brands} onSelectBrand={handleSelectBrand} />
        <PriceSort onSortChange={handleSortChange} />
      </div>
      <div style={{ backgroundColor: "#f3f3f3" }}>
        <h1
          style={{
            display: "flex",
            color: "#282872",
            justifyContent: "center",
          }}
        >
          {" "}
          KDU MARKETPLACE
        </h1>
        <div
          style={{
            display: "flex",
            flexWrap: "wrap",
            gap: "1em",
            marginLeft: "15em",
            marginRight: "15em",
          }}
        >
          {filteredProducts.map((product) => (
            <div
              key={product.id}
              style={{
                flex: "1 0 calc(25% - 20px)",
                maxWidth: "calc(25% - 20px)",
                border: "1px solid #ddd",
                borderRadius: "8px",
                overflow: "hidden",
              }}
            >
              <Link
                to={`/product/${product.id}`}
                onClick={() => setSelectedProduct(product)}
                style={{
                  textDecoration: "none",
                  color: "inherit",
                  display: "block",
                }}
              >
                <div
                  style={{
                    height: "300px",
                    overflow: "hidden",
                    paddingTop: "10px",
                  }}
                >
                  <img
                    src={product.image}
                    alt={product.title}
                    style={{
                      width: "100%",
                      height: "100%",
                      objectFit: "cover",
                    }}
                  />
                </div>
                <div
                  style={{
                    padding: "10px",
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "flex-end",
                    height: "70px",
                  }}
                >
                  <h2
                    style={{
                      margin: "0",
                      fontSize: "16px",
                      overflow: "hidden",
                      whiteSpace: "nowrap",
                    }}
                  >
                    {product.title.split(" ").slice(0, 4).join(" ")}
                  </h2>
                  <p
                    style={{
                      margin: "0",
                      fontSize: "14px",
                      fontWeight: "bold",
                    }}
                  >
                    ${product.price}
                  </p>
                </div>
              </Link>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ProductList;
