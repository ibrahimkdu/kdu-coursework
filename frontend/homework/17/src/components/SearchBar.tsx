import React from "react";
import Logo from "./images/search.png"; 

interface SearchBarProps {
  onSearch: (query: string) => void;
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearch }) => {
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    onSearch(e.target.value);
  };

  return (
    <div
      style={{
        display: "flex",
        padding: "5px",
        marginRight: "50em",
        marginLeft: "2em",
      }}
    >
      <input type="text" placeholder="Search..." onChange={handleInputChange} />
      <img
        src={Logo}
        alt=""
        style={{ height: "2em", marginLeft: "0.25em" }}
      />{" "}
  
    </div>
  );
};

export default SearchBar;
