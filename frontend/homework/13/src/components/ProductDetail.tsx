import React, { useContext } from "react";
import { useParams } from "react-router-dom";
import { ProductContext } from "../context/ProductContext";

const ProductDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const { selectedProduct } = useContext(ProductContext);

  return (
    <div style={{ backgroundColor: "#f3f3f3" }}>
      {selectedProduct && selectedProduct.id == Number(id) && (
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            backgroundColor: "#f3f3f3",
          }}
        >
          <h2
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            {selectedProduct.title}
          </h2>
          <p>{selectedProduct.description}</p>
          <p>Price: ${selectedProduct.price}</p>
          <p>Rating: {selectedProduct.rating.rate}</p>
          <img src={selectedProduct.image} alt={selectedProduct.title} />
        </div>
      )}
    </div>
  );
};

export default ProductDetail;
