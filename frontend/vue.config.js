module.exports = {
  // Change build paths to make them Maven compatible
  outputDir: 'target/dist',
  assetsDir: 'static',
  // proxy all webpack dev-server requests starting with /api
  // to our Spring Boot backend (localhost:8088) using http-proxy-middleware
  // see https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8088',
        ws: true,
        changeOrigin: true
      },
      '/images': {
        target: 'http://localhost:8088'
      }
    }
  }
};
