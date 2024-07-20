const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    mode: 'development',
    devtool: 'source-map',
    entry: {
        'create-post': './js/create-post.js',
        'index': './js/index.js'
    },
    output: {
        path: path.resolve(__dirname, 'static'),
        filename: '[name].js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: 'babel-loader',
                exclude: '/node-modules/'
            },
            {
                test: /\.s[ac]ss$/i,
                use: [
                    MiniCssExtractPlugin.loader,
                    { loader: 'css-loader', options: { url: false } },
                    'postcss-loader',
                    'sass-loader'
                ]
            }
        ]
    },
    plugins: [
        new CopyPlugin({
            patterns: [
                { from: 'asset', to: 'asset' },
                { from: 'fonts', to: 'fonts' }
            ]
        }),
        new CleanWebpackPlugin(),
        new MiniCssExtractPlugin({ filename: 'index.css' })
    ]
}
