import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchTransactions } from "../thunk/fetchTransaction";
import { filterTransactionsByStatus } from "../redux/TransactionSlice";
import NavbarStock from "./NavbarStock";

const Portfolio: React.FC = () => {
  const dispatch = useDispatch();
  const [selectedStock, setSelectedStock] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState(1);
  const transactionsPerPage = 10;

  const allTransactions = useSelector(
    (state: any) => state.transactions.transactions
  );

  const filteredTransactions = allTransactions.filter((transaction: any) => {
    if (!selectedStock) return true;
    return transaction.stock_name
      .toLowerCase()
      .includes(selectedStock.toLowerCase());
  });

  const statusFilter = useSelector(
    (state: any) => state.transactions.filterStatus
  );

  const filteredByStatus = statusFilter
    ? filteredTransactions.filter(
        (transaction: any) => transaction.status === statusFilter
      )
    : filteredTransactions;

  const status = useSelector((state: any) => state.transactions.status);
  const error = useSelector((state: any) => state.transactions.error);

  useEffect(() => {
    dispatch(fetchTransactions());
  }, [dispatch]);

  useEffect(() => {
    setCurrentPage(1); // Reset current page when search changes
  }, [selectedStock, statusFilter]);

  const handleStockChange = (stockName: string) => {
    setSelectedStock(stockName === selectedStock ? null : stockName);
  };

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleStatusFilter = (status: string) => {
    dispatch(filterTransactionsByStatus({ status }));
  };

  return (
    <>
      <NavbarStock />
      <div style={{ display: "flex", flexDirection: "column" }}>
        <div
          style={{
            width: "30%",
            border: "1px solid black",
            borderRadius: "30px",
            height: "30rem",
            position: "fixed",
            marginLeft: "90px",
            marginTop: "25px",
            backgroundColor: "#e9ecef",
            zIndex: 1,
          }}
        >
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
              borderBottom: "1px solid #6f7072",
            }}
          >
            <div
              style={{
                fontFamily: "Poppins",
                fontSize: "20px",
                margin: "0.5rem",
              }}
            >
              FILTER
            </div>
            <button
              style={{
                fontFamily: "Poppins",
                fontSize: "20px",
                color: "#1b72c2",
                border: "none",
                backgroundColor: "transparent",
                cursor: "pointer",
                margin: "0.5rem",
              }}
              onClick={() => setSelectedStock(null)}
            >
              Clear All
            </button>
          </div>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
              borderBottom: "1px solid #6f7072",
            }}
          >
            <input
              style={{
                width: "80%",
                fontFamily: "poppins",
                height: "2rem",
                borderRadius: "5px",
                marginTop: "1rem",
                marginBottom: "1.3rem",
              }}
              placeholder="Search For a Stock"
              value={selectedStock || ""}
              onChange={(e) => setSelectedStock(e.target.value)}
            />
          </div>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
              borderBottom: "1px solid #6f7072",
            }}
          >
            <div
              style={{
                fontFamily: "Poppins",
                color: "#6f7072",
                paddingLeft: "1rem",
                marginTop: "0.6rem",
                paddingBottom: "0.6rem",
                borderBottom: "1px solid #6f7072",
              }}
            >
              Pass/Fail
            </div>
            <input
              type="checkbox"
              value="Passed"
              onChange={() => handleStatusFilter("Passed")}
            />
            <label> Passed</label>
            <input
              type="checkbox"
              value="Failed"
              onChange={() => handleStatusFilter("Failed")}
            />
            <label> Failed</label>
            <br />
          </div>
          <div
            style={{
              overflow: "scroll",
              display: "block",
              height: "12rem",
              fontFamily: "Poppins",
              color: "#6f7072",
              paddingLeft: "1rem",
              paddingTop: "1rem",
            }}
          >
            {filteredByStatus
              .slice(
                (currentPage - 1) * transactionsPerPage,
                currentPage * transactionsPerPage
              )
              .map((transaction: any, index: number) => (
                <div key={index}>
                  <input
                    type="checkbox"
                    checked={selectedStock === transaction.stock_name}
                    onChange={() => handleStockChange(transaction.stock_name)}
                  />
                  <label>{transaction.stock_name}</label>
                  <br />
                </div>
              ))}
          </div>
        </div>
        <div
          style={{
            marginLeft: "20em",
            marginTop: "auto",
            marginBottom: "1em",
            alignSelf: "center",
          }}
        >
          {status === "loading" && <div>Loading...</div>}
          {status === "failed" && <div>Error: {error}</div>}
          {status === "succeeded" && (
            <div>
              {filteredByStatus
                .slice(
                  (currentPage - 1) * transactionsPerPage,
                  currentPage * transactionsPerPage
                )
                .map((transaction: any, index: number) => (
                  <div
                    key={index}
                    style={{
                      borderBottom: "1px solid #ccc",
                      padding: "10px 0",
                      display: "flex",
                      alignItems: "center",
                    }}
                  >
                    <div style={{ width: "26%" }}>{transaction.stock_name}</div>
                    <div style={{ width: "26%" }}>
                      {transaction.stock_symbol}
                    </div>
                    <div style={{ width: "26%" }}>
                      {transaction.transaction_price}
                    </div>
                    <div style={{ display: "flex", alignItems: "center" }}>
                      <p>{transaction.timestamp}</p>
                      <div
                        style={{
                          marginLeft: "10px",
                          backgroundColor:
                            transaction.status === "Passed" ? "green" : "red",
                          height: "10px",
                          width: "10px",
                          borderRadius: "50%",
                        }}
                      ></div>
                    </div>
                  </div>
                ))}
            </div>
          )}
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              marginTop: "1rem",
            }}
          >
            {Array.from(
              {
                length: Math.min(
                  Math.ceil(filteredByStatus.length / transactionsPerPage),
                  10
                ),
              },
              (_, i) => (
                <button key={i + 1} onClick={() => handlePageChange(i + 1)}>
                  {i + 1}
                </button>
              )
            )}
          </div>
        </div>
      </div>
    </>
  );
};

export default Portfolio;
