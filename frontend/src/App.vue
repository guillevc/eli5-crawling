<template>
  <div id="app">
    <div class="app-content">
      <router-view/>
    </div>
    <footer class="footer">
      <div class="content has-text-centered">
        <p>
          <strong>Crawling and searching <a href="https://reddit.com/r/explainlikeimfive" target="_blank">reddit.com/r/explainlikeimfive</a></strong> by <a href="https://github.com/guillevc" target="_blank">Guillermo Varela</a> and <a href="#" target="_blank">Luis Cruz</a>.
        </p>
      </div>
    </footer>
  </div>
</template>

<style lang="scss">
@import 'scss/custom.scss';

html, body, #app {
  height: 100%;
}

body, #app {
  display: flex;
  flex-direction: column;
}

.app-content {
  flex: 1 0 auto;
}

.footer {
  flex-shrink: 0;
}
</style>
