// Header component
import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "./redux/store";
import { setSearchTerm } from "./redux/todoSlice";
import "./Header.scss";

const Header = () => {
  const searchTerm = useSelector((state: RootState) => state.todo.searchTerm);
  const dispatch = useDispatch();

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setSearchTerm(event.target.value));
  };

  return (
    <div id="header" className="header">
      <div className="title">
        <h1>List Items</h1>
      </div>
      <div id="search-bar" className="search-bar">
        <input
          type="text"
          value={searchTerm}
          onChange={handleSearchChange}
          placeholder="Search Items"
          data-testid="search-box"
        />
      </div>
    </div>
  );
};

export default Header;
