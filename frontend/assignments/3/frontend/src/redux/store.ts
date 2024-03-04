import { configureStore } from "@reduxjs/toolkit";
import stockReducer from "./stockSlice";
import transactionsReducer from "./TransactionSlice";
export const store = configureStore({
  reducer: {
    stocks: stockReducer,
    transactions: transactionsReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
