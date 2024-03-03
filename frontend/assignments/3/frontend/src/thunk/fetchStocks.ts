import { createAsyncThunk } from "@reduxjs/toolkit";
import { Stock } from '../types'; // Assuming Stock type is defined somewhere

export const fetchStocks = createAsyncThunk<Stock[], void>(
  'stocks/fetchStocks',
  async () => {
    try {
      const response = await fetch('https://dev-1gyvfva3nqtb0v4.api.raw-labs.com/mock/stocks');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      console.log(data);
      return data;
    } catch (error) {
      throw new Error('Error fetching stocks');
    }
  }
) as any;

