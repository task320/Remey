<template>
	<div id="app">
		<FormPostShopping/>
		<br />
		<br />
		<ShoppingList/>
	</div>
</template>

<script>

import ShoppingList from './components/ShoppingList.vue'
import FormPostShopping from './components/FormPostShopping.vue'

export default {
	components: {
		ShoppingList,
		FormPostShopping
	}
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
