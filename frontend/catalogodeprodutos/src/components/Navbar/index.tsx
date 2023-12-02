import { Link, NavLink } from 'react-router-dom';
import {
  TokenData,
  getTokenData,
  isAuthenticated,
  removeAuthData,
} from 'util/requests';
import { useState, useEffect } from 'react';

import './styles.css';
import '@popperjs/core';
import 'bootstrap/js/src/collapse';
import history from 'util/history';

type authData = {
  authenticated: boolean;
  tokenData?: TokenData;
};

const Navbar = () => {
  const [authData, setAuthData] = useState<authData>({ authenticated: false });

  useEffect(() => {
    if (isAuthenticated()) {
      setAuthData({
        authenticated: true,
        tokenData: getTokenData(),
      });
    } else {
      setAuthData({
        authenticated: false,
      });
    }
  }, []);

  const handleClick = (event: React.MouseEvent<HTMLAnchorElement>) => {
    event.preventDefault();
    removeAuthData();
    setAuthData({
      authenticated: false,
    });
    history.replace('/');
  };

  return (
    <nav className="navbar navbar-expand-md navbar-dark bg-primary main-nav">
      <div className="container-fluid">
        <Link to="/" className="nav-logo-text">
          <h4>DS Catalog</h4>
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#dscatalog-navbar"
          aria-controls="dscatalog-navbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="dscatalog-navbar">
          <ul className="navbar-nav offset-md-2 main-menu">
            <li>
              <NavLink to="/" activeClassName="active" exact>
                HOME
              </NavLink>
            </li>
            <li>
              <NavLink to="/products" activeClassName="active">
                CATÁLOGO
              </NavLink>
            </li>
            <li>
              <NavLink to="/admin" activeClassName="active">
                ADMIN
              </NavLink>
            </li>
          </ul>
        </div>
        <div className="nav-login-logout">
          {authData.authenticated ? (
            <a href="#logout" onClick={handleClick}>
              <span className="nav-username">
                {authData.tokenData?.user_name}
              </span>
              <h4>LOGOUT</h4>
            </a>
          ) : (
            <Link to="/admin/auth/login">
              <h4>LOGIN</h4>
            </Link>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
