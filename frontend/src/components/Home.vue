<template>
  <div class="section">
    <div class="container">
      <div class="wrapper">
        <figure class="image">
          <router-link :to="{name: 'home'}">
            <img src="../assets/explainlikeimfive.png">
          </router-link>
        </figure>
        <SearchInput class="search-input" @submit="onSearchSubmit"></SearchInput>
      </div>
    </div>
  </div>
</template>

<script>
import SearchInput from './SearchInput.vue';
import categories from '../model/categories';

export default {
  name: 'Home',
  data: () => ({
    categories
  }),
  methods: {
    onSearchSubmit(q, c) {
      this.$router.push({ name: 'search', query: { q, c } });
    }
  },
  components: {
    SearchInput
  }
};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';

.section, .container, .wrapper {
  height: 100%;
}

.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding-bottom: 100px;
}

.image {
  max-width: 400px;
  margin-bottom: 20px;
}

.search-input {
  min-width: 65%;
  margin: 0 auto;
}
</style>
