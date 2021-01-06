import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Orders from './Orders';
import Home from './Home';
import Navbar from './Navbar';

function Routes() {

  return (
    <BrowserRouter>
      <Navbar />

      <Switch>
        <Route 
          path="/orders" 
          component={Orders}
        />

        <Route
          path="/"
          component={Home}
          exact
        />
      </Switch>
    </BrowserRouter>
  );
}

export default Routes;