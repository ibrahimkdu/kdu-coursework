import React from 'react';
import { useDispatch } from 'react-redux';
import { sortedItems } from '../redux/productSlice';

const PriceSort: React.FC = () => {
  const dispatch = useDispatch();

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    dispatch(sortedItems(e.target.value));
  };

  return (
    <div>
      <select onChange={handleSelectChange}>
        <option value="">Sort by Price</option>
        <option value="asc">Low to High</option>
        <option value="desc">High to Low</option>
      </select>
    </div>
  );
};

export default PriceSort;


