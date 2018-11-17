const {VueLoaderPlugin} = require('vue-loader');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const path = require('path');

module.exports = {
  mode: 'development',
  entry: './src/main.js',
  output: {
  	path: path.resolve(__dirname, '../../../Remey/src/asset'),
  	filename: 'js/main.js'
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        use: 'vue-loader'
      }
    ]
  },
  plugins: [
   new webpack.DefinePlugin({
	  BASE_URL: JSON.stringify('')
	}),
    new VueLoaderPlugin(),
    new HtmlWebpackPlugin({
     inject: true,
     filename: 'main.html',
     template: 'src/assets/main.html'
    }),

  ]
}