"devServer": {
    "historyApiFallback": true,
    "proxy": {
      "/api": {
        "target" : "http://localhost:4200",
        "secure": false
      }
    }
  }