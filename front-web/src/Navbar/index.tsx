import React from 'react';

import { ReactComponent as Logo } from '../assets/logo.svg';
import './styles.css';

function Navbar() {

  return (
    <nav className="main-navbar">
      <Logo />
      <a className="logo-text">DS Delivery</a>
    </nav>
  );
}

export default Navbar;