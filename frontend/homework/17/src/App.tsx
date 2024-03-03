import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ProductList from "./components/ProductList"; // Correct import path
import ProductDetail from "./components/ProductDetail"; // Correct import path
import { getProducts } from "./thunk/getProducts";

function App() {
  const reduxDispatch = useDispatch();

  useEffect(() => {
    reduxDispatch(getProducts());
  }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ProductList />} />
        <Route path="/product/:id" element={<ProductDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
