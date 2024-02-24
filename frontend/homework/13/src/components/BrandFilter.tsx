import React from "react";

interface BrandFilterProps {
  brands: string[];
  onSelectBrand: (brand: string) => void;
}

const BrandFilter: React.FC<BrandFilterProps> = ({ brands, onSelectBrand }) => {
  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    onSelectBrand(e.target.value);
  };

  return (
    <select onChange={handleSelectChange}>
      <option value="">All Brands</option>
      {brands.map((brand) => (
        <option key={brand} value={brand}>
          {brand}
        </option>
      ))}
    </select>
  );
};

export default BrandFilter;
