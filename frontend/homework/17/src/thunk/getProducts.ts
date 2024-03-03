import { createAsyncThunk } from "@reduxjs/toolkit";

export const getProducts = createAsyncThunk("getProducts", async () => {
  try {
    const res = await fetch("https://fakestoreapi.com/products");
    const data = await res.json();
    console.log(data);
    return data;
  } catch (err) {
    throw new Error("Wrong URL");
  }
}) as any;
