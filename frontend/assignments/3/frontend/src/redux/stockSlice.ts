import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Stock } from '../types';
import { fetchStocks } from '../thunk/fetchStocks';

interface StockState {
  stocks: Stock[];
  watchlist: Stock[];
  status: 'idle' | 'loading' | 'succeeded' | 'failed';
  error: string | null;
}

const initialState: StockState = {
  stocks: [],
  watchlist: [],
  status: 'idle',
  error: null,
};

const stockSlice = createSlice({
  name: 'stocks',
  initialState,
  reducers: {
    addToWatchlist(state, action: PayloadAction<Stock>) {
      state.watchlist.push(action.payload);
      console.log(state.watchlist);
    },
    removeFromWatchlist(state, action: PayloadAction<string>) {
      state.watchlist = state.watchlist.filter(stock => stock.stock_symbol !== action.payload);
      console.log(state.watchlist);
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchStocks.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchStocks.fulfilled, (state, action: PayloadAction<Stock[]>) => {
        state.status = 'succeeded';
        state.stocks = action.payload;
      })
      .addCase(fetchStocks.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message ?? 'An error occurred.';
      });
  },
});

export const { addToWatchlist, removeFromWatchlist } = stockSlice.actions;
export default stockSlice.reducer;

