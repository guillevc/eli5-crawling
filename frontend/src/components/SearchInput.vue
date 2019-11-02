<template>
  <form class="field has-addons has-addons-centered"
      @submit.prevent="$emit('submit', mySearchQuery, myCategory)">
    <p class="control">
      <span class="select">
        <select v-model="myCategory">
          <option value=''>All categories</option>
          <option v-for="(value, key) in categories" :key="key" :value="key">{{value.name}}</option>
        </select>
      </span>
    </p>
    <p class="control is-expanded">
      <input v-model="mySearchQuery" class="input" type="text" placeholder="Ask a question">
    </p>
    <p class="control">
      <button type="submit" class="button is-primary">
        Search
      </button>
    </p>
  </form>
</template>

<script>
import categories from '../model/categories';

export default {
  name: 'SearchInput',
  props: {
    category: {
      type: String,
      default: ''
    },
    searchQuery: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      myCategory: this.category,
      mySearchQuery: this.searchQuery,
      categories
    };
  }
};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';
</style>
