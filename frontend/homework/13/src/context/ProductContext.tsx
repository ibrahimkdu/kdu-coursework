import React, { createContext, useState, ReactNode } from "react";
import { MobileApi } from "../types/MobileApi";

interface ContextProps {
  selectedProduct: MobileApi | null;
  setSelectedProduct: React.Dispatch<React.SetStateAction<MobileApi | null>>;
}

export const ProductContext = createContext<ContextProps>({
  selectedProduct: null,
  setSelectedProduct: () => {},
});

export const ProductProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [selectedProduct, setSelectedProduct] = useState<MobileApi | null>(
    null
  );

  return (
    <ProductContext.Provider value={{ selectedProduct, setSelectedProduct }}>
      {children}
    </ProductContext.Provider>
  );
};
