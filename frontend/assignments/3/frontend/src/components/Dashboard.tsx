import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../redux/store";
import { Link } from "react-router-dom";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import { fetchStocks } from "../thunk/fetchStocks";
import { addToWatchlist, removeFromWatchlist } from "../redux/stockSlice";
import { Stock } from "../types";
import Navbar from "./Navbar";

const Dashboard: React.FC = () => {
  const dispatch = useDispatch();
  const stocks = useSelector((state: RootState) => state.stocks.stocks);
  const watchlist = useSelector((state: RootState) => state.stocks.watchlist);
  const [page, setPage] = useState<number>(1);
  const stocksPerPage = 10;

  useEffect(() => {
    dispatch(fetchStocks());
  }, [dispatch]);

  const addToWatchlistHandler = (stock: Stock) => {
    dispatch(addToWatchlist(stock));
  };

  const removeFromWatchlistHandler = (stockSymbol: string) => {
    dispatch(removeFromWatchlist(stockSymbol));
  };

  const pageCount = Math.ceil(stocks.length / stocksPerPage);
  const indexOfLastStock = page * stocksPerPage;
  const indexOfFirstStock = indexOfLastStock - stocksPerPage;
  const currentStocks = stocks.slice(indexOfFirstStock, indexOfLastStock);

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

  const addIcon: React.CSSProperties = {
    color: "#1871c2",
    fontSize: "20px",
    textDecoration: "none",
    listStyleType: "none",
    cursor: "pointer",
  };

  const removeIcon: React.CSSProperties = {
    color: "red",
    fontSize: "20px",
    textDecoration: "none",
    listStyleType: "none",
    cursor: "pointer",
  };

  return (
    <>
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
          {currentStocks.map((stock, index) => (
            <div style={stockDiv} key={index}>
              <div style={company1}>
                <Link to={`/stock`} style={{ textDecoration: "none" }}>
                  <h3>{stock.stock_name}</h3>
                </Link>
              </div>
              <div style={price1}>
                <div>
                  <h3>{stock.base_price}</h3>
                </div>
                <div style={watchlist1}>
                  {watchlist.find(
                    (watchlistStock) =>
                      watchlistStock.stock_symbol === stock.stock_symbol
                  ) ? (
                    <button
                      style={removeIcon}
                      onClick={() =>
                        removeFromWatchlistHandler(stock.stock_symbol)
                      }
                    >
                      Remove
                    </button>
                  ) : (
                    <button
                      style={addIcon}
                      onClick={() => addToWatchlistHandler(stock)}
                    >
                      Add
                    </button>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
        <div style={paging}>
          <Pagination
            count={pageCount}
            page={page}
            onChange={(event, value) => setPage(value)}
            color="primary"
            style={{ margin: "auto" }}
          />
        </div>
      </div>
    </>
  );
};

export default Dashboard;
