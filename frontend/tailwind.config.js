const colors = require('tailwindcss/colors')

module.exports = {
  purge: ['./src/**/*.html', './src/**/*.css'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        white: colors.white,
        emerald: colors.emerald,
        lightblue: colors.lightBlue,
        orange: colors.orange
      },
      outline: {
        orange: '2px solid #9A3412' // orange-800
      }
    }
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
