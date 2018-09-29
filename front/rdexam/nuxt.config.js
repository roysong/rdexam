module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: 'rdexam',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'research&develop depart&apos;s online exam system' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*---------add elementui cfg start--------*/
  /*
  ** Global CSS
  */
  css: [
    'element-ui/lib/theme-chalk/index.css'
  ],

  /*
  ** Add element-ui in our app, see plugins/element-ui.js file
  */
  plugins: [
    {src: '@/plugins/ElementUI',ssr: true}
  ],
  /*---------add elementui cfg end----------*/


  /*---------add axios cfg start--------*/
  modules: [
    '@nuxtjs/axios'
  ],
  axios: {
    prefix: '/api/',
    proxy: true
  },
  proxy: {
    '/api/': {target: 'http://localhost:1329',pathRewrite: {'^/api/': ''}}
  },
  /*---------add axios cfg end----------*/
  /*
  ** Build configuration
  */
  build: {
  }
}

