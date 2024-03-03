import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../redux/store';
import { filterItems } from '../redux/productSlice';

const BrandFilter: React.FC = () => {
  const items = useSelector((state: RootState) => state.product.items);
  const dispatch = useDispatch();
  const brandsSet = new Set<string>();
  items.forEach(item => brandsSet.add(item.category));
  const brands = Array.from(brandsSet);

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    dispatch(filterItems(e.target.value));
  };

  return (
    <div>
      <select onChange={handleSelectChange}>
        <option value="">All Brands</option>
        {brands.map((brand, index) => (
          <option key={index} value={brand}>
            {brand}
          </option>
        ))}
      </select>
    </div>
  );
};

export default BrandFilter;

