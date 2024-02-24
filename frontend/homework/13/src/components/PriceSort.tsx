import React from "react";

interface PriceSortProps {
  onSortChange: (sortOrder: string) => void;
}

const PriceSort: React.FC<PriceSortProps> = ({ onSortChange }) => {
  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    onSortChange(e.target.value);
  };

  return (
    <select onChange={handleSelectChange} style={{ marginLeft: "30em" }}>
      <option value="">Sort by Price</option>
      <option value="asc">Low to High</option>
      <option value="desc">High to Low</option>
    </select>
  );
};

export default PriceSort;
