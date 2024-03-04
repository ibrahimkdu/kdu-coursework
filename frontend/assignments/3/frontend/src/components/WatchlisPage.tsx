import React from "react";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import Navbar from "./Navbar";
import NavbarStock from "./NavbarStock";
const WatchlistPage: React.FC = () => {
  const watchlist = useSelector((state: RootState) => state.stocks.watchlist);

  const mainContainer: React.CSSProperties = {
    fontFamily: "Poppins",
    margin: "auto",
    width: "70%",
    height: "37rem",
    border: "2.4px solid black",
    borderRadius: "20px",
    overflow: "scroll",
  };

  const blockTitle: React.CSSProperties = {
    display: "flex",
    justifyContent: "space-between",
    flexDirection: "row",
    height: "3rem",
    borderBottom: "2px solid black",
    width: "100%",
    alignContent: "center",
    alignItems: "center",
    fontSize: "27px",
  };

  const company: React.CSSProperties = {
    marginLeft: "20px",
  };

  const company1: React.CSSProperties = {
    marginLeft: "60px",
  };

  const price: React.CSSProperties = {
    marginRight: "20px",
    display: "flex",
    flexDirection: "row",
  };

  const price1: React.CSSProperties = {
    marginRight: "20px",
    display: "flex",
    flexDirection: "row",
    width: "28%",
    justifyContent: "space-between",
  };

  const watchlist1: React.CSSProperties = {
    marginLeft: "40px",
    marginRight: "20px",
    width: "30%",
    alignItems: "center",
    alignContent: "center",
    paddingTop: "13px",
  };

  const stockDiv: React.CSSProperties = {
    display: "flex",
    justifyContent: "space-between",
    flexDirection: "row",
    width: "100%",
    alignContent: "center",
    alignItems: "center",
    fontSize: "12px",
    borderBottom: "1px solid grey",
  };

  const paging: React.CSSProperties = {
    display: "flex",
    justifyContent: "center",
    alignContent: "center",
    alignItems: "center",
    marginTop: "5px",
  };

  return (
    <>
      <NavbarStock />
      <Navbar />
      <div style={mainContainer}>
        <div style={blockTitle}>
          <div style={company}>Company</div>
          <div style={price}>
            <div>BasePrice</div>
            <div style={watchlist1}>Watchlist</div>
          </div>
        </div>
        <div>
          {watchlist.map((stock, index) => (
            <div style={stockDiv} key={index}>
              <div style={company1}>
                <h3>{stock.stock_name}</h3>
              </div>
              <div style={price1}>
                <div>
                  <h3>{stock.base_price}</h3>
                </div>
                <div style={watchlist1}></div>
              </div>
            </div>
          ))}
        </div>
        <Stack style={paging} spacing={2}>
          <Pagination
            count={Math.ceil(watchlist.length / 10)} // Assuming 10 items per page
            color="primary"
          />
        </Stack>
      </div>
    </>
  );
};

export default WatchlistPage;
