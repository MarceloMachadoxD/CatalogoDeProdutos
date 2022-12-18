import Home from 'pages/Home';
import Admin from 'pages/Admin';
import Navbar from 'components/Navbar';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Catalog from 'pages/Catalog';
import ProductDetails from 'pages/ProductDetails';

const Router = () => {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route index element={<Home />} />
          <Route path="products" element={<Catalog />} />
          <Route path="products/:productId" element={<ProductDetails />} />
          <Route path="admin" element={<Admin />}>
            <Route index element={<h1>Admin</h1>} />
            <Route path="products" element={<h1>Product CRUD</h1>} />
            <Route path="categories" element={<h1>Categories CRUD</h1>} />
            <Route path="users" element={<h1>Users CRUD</h1>} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default Router;
