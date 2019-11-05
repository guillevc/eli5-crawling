<template>
  <div id="app">
    <div class="app-content">
      <router-view/>
    </div>
    <TheFooter class="app-footer"></TheFooter>
  </div>
</template>

<script>
import TheFooter from './components/TheFooter.vue';

export default {
  name: 'App',
  components: {
    TheFooter
  }
};
</script>

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

.app-footer {
  flex-shrink: 0;
}
</style>
