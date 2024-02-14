const { src, dest } = require('gulp');
const sass = require('gulp-sass')(require('sass'));

exports.default = () => src('./css/**/*.scss')
    .pipe(sass().on('error', sass.logError))
    .pipe(dest('./css'));
