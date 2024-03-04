import { createAsyncThunk } from "@reduxjs/toolkit";
import { Stock } from "../types";

export const fetchTransactions = createAsyncThunk<Stock[], void>(
  "transactions/fetchTransactions",
  async () => {
    try {
      const response = await fetch(
        "https://dev-1gyvfva3nqtb0v4.api.raw-labs.com/mock/portfolio-transactions"
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      console.log(data);
      return data;
    } catch (error) {
      throw new Error("Error fetching stocks");
    }
  }
) as any;
