import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { fetchTransactions } from "../thunk/fetchTransaction";

interface TransactionState {
  transactions: any[]; 
  status: 'idle' | 'loading' | 'succeeded' | 'failed';
  error: string | null;
  filterStatus: string | null; 
}

const initialState: TransactionState = {
  transactions: [],
  status: 'idle',
  error: null,
  filterStatus: null, // Initialize filterStatus as null
};

const transactionsSlice = createSlice({
  name: 'transactions',
  initialState,
  reducers: {
    filterTransactionsByStatus(state, action: PayloadAction<{ status: string }>) {
      state.filterStatus = action.payload.status; // Update filterStatus with selected status
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchTransactions.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchTransactions.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.transactions = action.payload; // Update transactions state with fetched data
      })
      .addCase(fetchTransactions.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message || 'Failed to fetch transactions';
      });
  },
});

export const { filterTransactionsByStatus } = transactionsSlice.actions;

export default transactionsSlice.reducer;
