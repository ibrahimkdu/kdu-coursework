import React from "react";
import './Header.scss';

interface HeaderProps {
  searchTerm: string;
  onSearchChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const Header = ({ searchTerm, onSearchChange }: HeaderProps) => {
  return (
    <div className="header">
      <div className="title">
        <h1>List Items</h1>
      </div>
      <div className="search-bar">
        <input
          type="text"
          value={searchTerm}
          onChange={onSearchChange}
          placeholder="Search Items"
        />
      </div>
    </div>
  );
}

export default Header;
