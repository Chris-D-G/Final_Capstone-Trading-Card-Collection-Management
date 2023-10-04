<template>
  <div
    class="d-flex justify-content-center mx-5 mt-5 border border-dark rounded-4 "
    id="messageTable"
  >
    <div class="d-flex flex-column flex-lg-row justify-content-between flex-fill px-5">
      <div id="messageId" class="d-flex flex-column">
        <p class="fs-4">Message ID</p>
        <router-link
          class="fs-5"
          v-for="(message, index) in messageList"
          v-bind:key="index"
        >
          {{ message.messageID }}
        </router-link>
      </div>
      <div id="sender" class="d-flex flex-column">
        <p class="fs-4">Sender</p>
        <p
          class="fs-5"
          v-for="(message, index) in messageList"
          v-bind:key="index"
        >
          {{ message.messageSender }}
        </p>
      </div>
      <div id="receipient" class="d-flex flex-column">
        <p class="fs-4">Receipient</p>
        <p
          class="fs-5"
          v-for="(message, index) in messageList"
          v-bind:key="index"
        >
          {{ message.messageReceiver }}
        </p>
      </div>
      <div id="dateTime" class="d-flex flex-column">
        <p class="fs-4">Created Date & Time</p>
        <p
          class="fs-5"
          v-for="(message, index) in messageList"
          v-bind:key="index"
        >
          {{ message.messageTimestamp }}
        </p>
      </div>
      <div id="readStatus" class="d-flex flex-column">
        <p class="fs-4">Read Status</p>
        <p
          class="fs-5"
          v-for="(message, index) in messageList"
          v-bind:key="index"
        >
          {{ message.read }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService.js";

export default {
  name: "message-table",
  data() {
    return {
      messageList: [],
    };
  },
  created() {
    MessageService.getMessagesForUser().then((response) => {
      this.messageList = response.data;
    });
  },
};
</script>

<style scoped>
#messageTable {
  background-color: #ffffffcc;
}
</style>