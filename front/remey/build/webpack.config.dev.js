'use strict'
const { VueLoaderPlugin } = require('vue-loader')
module.exports = {
  mode: 'development',
  entry: [
    './src/main.js'
  ],
  module: {
    rules: [
      {
        test: /\.vue$/,
        use: 'vue-loader'
      },
      {
        test: /\.js$/,
        loader: 'babel-loader'
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin()
  ]
}