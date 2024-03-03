import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { displayItems } from '../redux/productSlice';

const SearchBar: React.FC = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const dispatch = useDispatch();

  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(e.target.value);
    dispatch(displayItems(e.target.value));
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Search..."
        value={searchQuery}
        onChange={handleSearch}
      />
    </div>
  );
};

export default SearchBar;

